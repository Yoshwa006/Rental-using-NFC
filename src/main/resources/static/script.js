document.addEventListener("DOMContentLoaded", () => {
    const scanButton = document.getElementById("scanButton");
    const loadingScreen = document.getElementById("loadingScreen");
    const bikeDetails = document.getElementById("bikeDetails");
    const ownerDetails = document.getElementById("ownerDetails");
    const slider = document.getElementById("slider");
    const bikeImage = document.getElementById("bikeImage");
    const detailsContainer = document.getElementById("detailsContainer");

    async function scanNFC() {
        if (!("NDEFReader" in window)) {
            alert("NFC not supported on this device.");
            return;
        }

        function toggleMenu() {
            document.querySelector('.nav-links').classList.toggle('active');
        }


        try {
            // Hide scan button, show loading screen
            scanButton.style.display = "none";
            loadingScreen.style.display = "block";

            const reader = new NDEFReader();
            await reader.scan();

            reader.onreading = (event) => {
                setTimeout(() => { // Simulate loading delay
                    loadingScreen.style.display = "none"; // Hide loading screen

                    const decoder = new TextDecoder();
                    for (const record of event.message.records) {
                        const data = JSON.parse(decoder.decode(record.data));

                        // Display bike & owner details
                        bikeDetails.innerHTML = `<strong>Model:</strong> ${data.bikeModel} <br>
                                                 <strong>Number:</strong> ${data.bikeNumber}`;
                        ownerDetails.innerHTML = `<strong>Name:</strong> ${data.ownerName} <br>
                                                  <strong>Contact:</strong> ${data.ownerContact}`;

                        // Show bike image
                        bikeImage.src = data.imageURL;
                        slider.style.display = "block";

                        // Show details container
                        detailsContainer.style.display = "block";
                    }
                }, 2000); // 2s delay for loading effect
            };

        } catch (error) {
            console.log("NFC Scan Error:", error);
        }
    }

    scanButton.addEventListener("click", scanNFC);
});