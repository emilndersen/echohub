const useLocalMode = true; 
const API_BASE = "http://localhost:8080/api"; // если подключишь бэк

let currentUser = null;

// ---------------- ЛОГИН / РЕГИСТРАЦИЯ ----------------
document.getElementById("register-btn").onclick = () => {
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();
    if (!username || !password) return alert("Заполните все поля");
    
    if (useLocalMode) {
        let users = JSON.parse(localStorage.getItem("users") || "[]");
        if (users.find(u => u.username === username)) {
            alert("Такой пользователь уже есть!");
            return;
        }
        users.push({ username, password });
        localStorage.setItem("users", JSON.stringify(users));
        alert("Регистрация успешна!");
    }
};

document.getElementById("login-btn").onclick = () => {
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();

    if (useLocalMode) {
        let users = JSON.parse(localStorage.getItem("users") || "[]");
        let user = users.find(u => u.username === username && u.password === password);
        if (user) {
            currentUser = username;
            showBlog();
        } else {
            alert("Неверный логин или пароль");
        }
    }
};

// ---------------- ЛОГИКА ПОСТОВ ----------------
document.getElementById("post-btn").onclick = () => {
    const content = document.getElementById("post-content").value.trim();
    if (!content) return;

    if (useLocalMode) {
        let posts = JSON.parse(localStorage.getItem("posts") || "[]");
        posts.push({ id: Date.now(), user: currentUser, content });
        localStorage.setItem("posts", JSON.stringify(posts));
        renderPosts();
        document.getElementById("post-content").value = "";
    }
};

function renderPosts() {
    let posts = JSON.parse(localStorage.getItem("posts") || "[]");
    const postsDiv = document.getElementById("posts");
    postsDiv.innerHTML = "";
    posts.reverse().forEach(p => {
        const div = document.createElement("div");
        div.className = "post";
        div.innerHTML = `
            <strong>${p.user}</strong>: ${p.content}
            <div class="actions">
                ${p.user === currentUser ? `<button onclick="deletePost(${p.id})">Удалить</button>` : ""}
            </div>
        `;
        postsDiv.appendChild(div);
    });
}

function deletePost(id) {
    let posts = JSON.parse(localStorage.getItem("posts") || "[]");
    posts = posts.filter(p => p.id !== id);
    localStorage.setItem("posts", JSON.stringify(posts));
    renderPosts();
}

// ---------------- ПЕРЕКЛЮЧЕНИЕ ЭКРАНОВ ----------------
function showBlog() {
    document.getElementById("auth-section").style.display = "none";
    document.getElementById("blog-section").style.display = "block";
    document.getElementById("current-user").textContent = currentUser;
    renderPosts();
}

document.getElementById("logout-btn").onclick = () => {
    currentUser = null;
    document.getElementById("auth-section").style.display = "block";
    document.getElementById("blog-section").style.display = "none";
};
