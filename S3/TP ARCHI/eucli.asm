.ORIG x3000

        LD R1 , b
        LD R2 , c
        LD R0 , a
    LOOP    BRn SKIP
                ADD R2 , R2 , #1
                SUB R0 , R0 , R1
            BR LOOP

    SKIP    
            ADD R0 , R0 , R1
            ADD R2 , R2 , #-1


    END     HALT
            a .FILL #15     ; nombre / reste
            b .FILL #6      ; diviseur
            c .FILL #0      ; nombre de fois divisé
.END