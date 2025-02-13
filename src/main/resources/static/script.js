<script>
    function getQueryParam(param) {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get(param);
    }

    const itemId = getQueryParam("id");

    if (itemId) {
        const apiUrl = `https://nfc-rental-system2-1.onrender.com/api/items/${itemId}`;

        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                document.getElementById('itemName').innerText = data.itemName;
                document.getElementById('itemImage').src = data.images;
                document.getElementById('itemPrice').innerText = `$${data.price}`;
                document.getElementById('itemDescription').innerText = data.description;
                document.getElementById('itemOwner').innerText = data.ownerName;
                document.getElementById('itemContact').innerText = data.phone;
                document.getElementById('itemAvailability').innerText = data.available ? 'Available' : 'Not Available';
            })
            .catch(error => console.error('Error fetching item:', error));
    } else {
        alert("No item ID provided in the URL!");
    }
</script>
