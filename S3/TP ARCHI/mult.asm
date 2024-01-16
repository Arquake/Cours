.ORIG x3000

        LD R0 , a

        LD R1 , b

        BRzp SKIP1      
                NOT R1, R1
                ADD R1 , R1 , #1
                NOT R0 , R0
SKIP1

        LD R2 , c

        BRzp SKIP2
                NOT R2, R2
                ADD R2 , R2 , #1
                NOT R0 , R0
SKIP2

        LD R3 , d

        ADD R2, R2, #0

LOOP    BRz SKIP
                ADD R3, R3, R1

                ADD R2, R2, #-1

                BR LOOP
SKIP
        ADD R0, R0, #0
        BRzp SKIP3
                NOT R3, R3
                
SKIP3




END     HALT
            a .FILL #0      ; negative | R0
            b .FILL #-15    ; x | R1
            c .FILL #4      ; y | R2
            d .FILL #0      ; r | R3
.END