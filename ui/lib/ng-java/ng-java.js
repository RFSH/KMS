(function () {
    var hooked = false;
    var hookListeners = [];

    window.javangHook = function () {
        hooked = true;
        while (hookListeners.length) {
            var callback = hookListeners.pop();
            callback();
        }
    };

    window.javangBridge = {};

    window.registerScopeFunction = function (scope, functionName, func) {
        scope['functionName'] = func.apply;
    };

    window.onHook = function (callback) {
        if (hooked) {
            callback();
        } else {
            hookListeners.push(callback);
        }
    };

    window.changePage = function (page) {
        window.location.hash = page;
    }
})();


var app = angular.module("ng-java", []);

app.service("$ngJava", function() {
    var callback = undefined;
    this.ready = function (cb) {
        callback = cb;
    };

    this.signalReady = function() {
        if (callback) {
            callback();
        }
    };
});

function bridgeFunctionFactory(key, functionWrappers) {
    return function() {
        console.log("calling " + key);
        return functionWrappers[key].invoke.apply(functionWrappers[key], arguments);
    };
}

app.directive("ngJavaController", function ($ngJava) {
    function link (localScope, el, attrs) {
        var functionWrappers = {
        };
        onHook(function () {
            //javangBridge.registerController(localScope.ngJavaController, localScope, functionWrappers);
            javangBridge.registerController(attrs.ngJavaController, localScope, functionWrappers);
            for (key in functionWrappers) {
                localScope[key] = bridgeFunctionFactory(key, functionWrappers);
                //console.log(localScope[key](2));
            }
            $ngJava.signalReady();
        });
    }

    return {
        link: link
        //scope: {
        //    ngJavaController: '@'
        //}
    };
});

// Testing

