var loadingButton = document.querySelector('#loadingButton')
var signupButton = document.querySelector('#submitButton')
var form = document.querySelector('#signup-form');
form.addEventListener('submit', async (e) => {
  loadingButton.style.display = "block"
  signupButton.style.display = "none"
  }
  )