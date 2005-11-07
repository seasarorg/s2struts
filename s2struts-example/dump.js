function printAttribute(req) {
    var names = req.getAttributeNames();
    while (names.hasMoreElements()) {
        var name = names.nextElement();
        print("att: " + name + " = " + req.getAttribute(name));
    }
}

function printParameter(req) {
    var names = req.getParameterNames();
    while (names.hasMoreElements()) {
        var name = names.nextElement();
        print("prm: " + name + " = " + req.getParameter(name));
    }
}

function printSession(session) {
    var names = session.getAttributeNames();
    while (names.hasMoreElements()) {
        var name = names.nextElement();
        print("ses: " + name + " = " + session.getAttribute(name));
    }
}

function print(value) {
    java.lang.System.out.println("" + value);
}

var req = request.getUnderlyingContext();

print("------ req-path: " + request.getRequestedPath());
printParameter(req);
printAttribute(req);
printSession(req.getSession());
