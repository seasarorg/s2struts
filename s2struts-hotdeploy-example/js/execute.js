//
//
//

function go(inputTag, action) {
    if (action != null) {
         inputTag.form.action = inputTag.form.action.replace(/\/[^\/]*$/, action);
    }
    inputTag.form.method.value=inputTag.name;
}
