// JavaScript for disabling form submissions if there are invalid fields
(function () {
    'use strict';
    // Form example - please follow the guide below when making form in this project
    /*
     <div>
     <label for="formName">Password</label>
     <input class="form-control"
     type="password"
     id="password"
     name="password"
     placeholder="Enter password"
     data-value-missing="" -> data value is required
     data-pattern-mismatch="" -> check pattern of input (pass in regex)
     data-too-long="" -> check length
     data-too-short="" -> check length
     pattern="" // If needed
     max=""     // If needed
     min=""     // If needed
     required
     />
     <div class="invalid-feedback"></div>
     </div>
     */
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll('.needs-validation');
    var messageDisplayers = document.querySelectorAll('.invalid-feedback');
    // Loop over them and prevent submission
    forms.forEach(form => {
        form.addEventListener('submit', function (event) {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
                var ele = document.getElementsByTagName('input');
                for (var i = 0; i < ele.length; i++) {
                    var reason = ele[i].validity;
                    if (reason.valueMissing) {
                        messageDisplayers[i].innerHTML = ele[i].getAttribute("data-value-missing");
                    } else if (reason.patternMismatch) {
                        messageDisplayers[i].innerHTML = ele[i].getAttribute("data-pattern-mismatch");
                    } else if (reason.tooLong) {
                        messageDisplayers[i].innerHTML = ele[i].getAttribute("data-too-long");
                    } else if (reason.tooShort) {
                        messageDisplayers[i].innerHTML = ele[i].getAttribute("data-too-short");
                    }
                }
            }
            form.classList.add('was-validated');
        }, false);
    });
})();