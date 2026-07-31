const API_URL = "http://localhost:9090/users";

let allUsers = [];

// Load Users
window.onload = () => {
  loadUsers();
};

async function loadUsers() {
  try {
    const response = await fetch(API_URL);

    if (!response.ok) {
      throw new Error("Failed to load users");
    }

    allUsers = await response.json();

    displayUsers(allUsers);
  } catch (error) {
    setFeedback(error.message, true);
    console.error(error);
  }
}

// Display Users
function displayUsers(users) {
  const tableBody = document.getElementById("userTableBody");

  tableBody.innerHTML = "";

  if (users.length === 0) {
    tableBody.innerHTML = `
            <tr>
                <td colspan="4">No Users Found</td>
            </tr>
        `;

    return;
  }

  users.forEach((user) => {
    tableBody.innerHTML += `
            <tr>
                <td>${user.userID}</td>
                <td>${user.userName}</td>
                <td>${user.role}</td>
                <td>
                    <button class="edit"
                        onclick="editUser(${user.userID},'${user.userName}','${user.role}')">
                        Edit
                    </button>

                    <button class="delete"
                        onclick="deleteUser(${user.userID})">
                        Delete
                    </button>
                </td>
            </tr>
        `;
  });
}

// Save or Update User
async function saveUser() {
  const id = document.getElementById("userId").value;

  const user = {
    userName: document.getElementById("userName").value.trim(),

    role: document.getElementById("role").value.trim(),
  };

  if (!user.userName || !user.role) {
    setFeedback("Please fill all fields", true);

    return;
  }

  try {
    const url = id ? `${API_URL}/${id}` : API_URL;

    const method = id ? "PUT" : "POST";

    const response = await fetch(url, {
      method,

      headers: {
        "Content-Type": "application/json",
      },

      body: JSON.stringify(user),
    });

    if (!response.ok) {
      throw new Error("Operation Failed");
    }

    setFeedback(id ? "User Updated Successfully" : "User Added Successfully");

    clearForm();

    loadUsers();
  } catch (error) {
    setFeedback(error.message, true);
  }
}

// Edit User
function editUser(id, name, role) {
  document.getElementById("userId").value = id;

  document.getElementById("userName").value = name;

  document.getElementById("role").value = role;
}

// Delete User
async function deleteUser(id) {
  if (!confirm("Delete this user?")) {
    return;
  }

  try {
    const response = await fetch(`${API_URL}/${id}`, {
      method: "DELETE",
    });

    if (!response.ok) {
      throw new Error("Delete Failed");
    }

    setFeedback("User Deleted Successfully");

    loadUsers();
  } catch (error) {
    setFeedback(error.message, true);
  }
}

// Search User
function searchUser() {
  const keyword = document.getElementById("search").value.toLowerCase();

  const filtered = allUsers.filter(
    (user) =>
      user.userName.toLowerCase().includes(keyword) ||
      user.role.toLowerCase().includes(keyword),
  );

  displayUsers(filtered);
}

// Clear Form
function clearForm() {
  document.getElementById("userId").value = "";

  document.getElementById("userName").value = "";

  document.getElementById("role").value = "";
}

// Feedback
function setFeedback(message, error = false) {
  const feedback = document.getElementById("feedback");

  feedback.textContent = message;

  feedback.style.color = error ? "red" : "green";

  setTimeout(() => {
    feedback.textContent = "";
  }, 3000);
}
