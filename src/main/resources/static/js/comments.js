const postCommentElement = document.getElementById('postComment');
postCommentElement.addEventListener('click', createComment);

function createComment(){
    const commentTextElement = document.getElementById('message');
    const textContent = commentTextElement.value
    const routeId = document.querySelector('input[name="routeId"]').value

    fetch('http://localhost:8080/api/comments/create',
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({routeId,textContent })
        })
        .then(res => res.json())
        .then(res => {

            const id = res.id;
            const approved = res.approved;
            const authorName = res.authorUsername;
            const content = res.content;

            const comment = document.createElement('div');
            comment.classList.add('form-group');


            const contentElement = document.createElement('h4');
            const contentElementValue = document.createTextNode(content);
            contentElement.appendChild(contentElementValue);


            const nameElement = document.createElement('label');
            const nameElementValue = document.createTextNode(authorName);
            nameElement.appendChild(nameElementValue);

            console.log(nameElement);

            comment.appendChild(contentElement);
            comment.appendChild(nameElement);

            const allCommentsElement = document.getElementsByClassName('all-comments')[0];
            allCommentsElement.appendChild(comment);

            commentTextElement.value = '';
        })
}