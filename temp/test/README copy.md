function isKataEqual (k1, k2: Kata) -> boolean
    {Menghasilkan true jika k1 = k2}
    KAMUS LOKAL
        x : integer
    ALGORITMA
        if(k1.length != k2.length) then
            -> false
        else
            i <- 0
            found <- false
            while (i < length && found = false ) do
                if(k1.buffer[i] != k2.buffer[i]) then
                    found <- true
            if(found) then
                -> false
            else
                -> true