
/**
 * Handles submissions from the search bar as well as retrieving recipe results from Spoonacular.
 */
document.querySelector('form[name="search"]').addEventListener('submit', function(event) {
    event.preventDefault(); //prevent the form from submitting normally

    let searchValue = document.querySelector('.search_box').value;

    fetch(`http://localhost:8080/api/search?search=${encodeURIComponent(searchValue)}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data =>  {
            console.log("data: " + data);
            if (data.length == 0) {
                console.log('No recipes found');
                alert('No recipes found');
            } else {
                localStorage.setItem('searchTerm', searchValue);
                var responseData = encodeURIComponent(JSON.stringify(data));
                var redirectURL = "http://127.0.0.1:5500/Frontend/recipes.html?operation=" + encodeURIComponent("recipeSearch") + "&responseData=" + responseData;
                window.location.href = redirectURL;
            }
        })
        .catch((error) => {
            console.error('Error:', error);
        });

});

