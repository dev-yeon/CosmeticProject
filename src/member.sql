	select
		 c.usrid
		,c.usrname
	    ,c.email
	    ,s.usrid
		,s.skinproblem
	
		from
		cosmember c ,		 
		memberskininfo s,
		
		where
	c.usrid =s.usrid
		and
		c.usrid = #{usrid}
