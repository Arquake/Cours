.ORIG x3000
        LD R1 , a
        LD R0 , b
        LD R2 , c
        NOT R3 , R1
        ADD R3 , R3 , #1
LOOP    BRzp END
        MUL R0 , R0 , R2
        ADD R2 , R2 , #1
        ADD R3 , R3 , #1
        BR LOOP
END     HALT
        a .FILL #5
        b .FILL #1
        c .FILL #2
.END