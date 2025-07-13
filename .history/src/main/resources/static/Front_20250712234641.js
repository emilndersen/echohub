document.getElementById('postForm').addEventListener('submit', function(e) {
    e.preventDefault();

    const title = document.getElementById('postTitle').value.trim();
    const content = document.getElementById('postContent').value.trim();

    if (!title || !content) return;

    const postSection = document.querySelector('.posts');

    const postElem = document.createElement('article');
    postElem.classList.add('post');

    const h2 = document.createElement('h2');
    h2.textContent = title;

    const p = document.createElement('p');
    p.textContent = content;

    postElem.appendChild(h2);
    postElem.appendChild(p);

    postSection.appendChild(postElem);

    this.reset();
});
