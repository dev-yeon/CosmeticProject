	select
		 c.usrid
	    ,s.usrid
		,s.skinproblem	
		,p.productname
		,p.producttype
		,p.productprice
		,p.productsolution
		from
		cosmember c ,		 
		memberskininfo s,
		cosproduct p
		where
	c.usrid =s.usrid
		and
		c.usrid = #{usrid}
		and
		s.skinproblem =p.productsolution
		
		