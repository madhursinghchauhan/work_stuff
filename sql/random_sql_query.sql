FORMAT( {<time>}, 'yyyy-MM-dd', 'en-US' ) = FORMAT(DATEADD(day, -1, GETDATE()), 'yyyy-MM-dd', 'en-US')
