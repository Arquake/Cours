.ORIG x3000
    .global _start
    _start :
        LD R1 , #1
        LD R2 , #4
        call fact
    END


    fact :
        SUB R2 , #1
        BRzn END
        call fact
        ADD R2 , R2 , #1
        MUL R1 , R1 , R2
        ret
    END
.END