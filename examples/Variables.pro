class Variables

    method main requires () returns void
        var string str = "Hello, world!"
        printString(str)
        printString("Hello")
        
    method printString requires (string str) returns void
        print str