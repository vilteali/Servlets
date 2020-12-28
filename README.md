# Servlets

<Context>
	<Resource name="jdbc/Productos" auth="Container" type="javax.sql.DataSource" maxActive="15"
		maxIdle="3" maxWait="5000" username="root" password="" driverClassName="com.mysql.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/productos?zeroDateTimeBehavior=convertToNull">
	</Resource>
</Context>

En el archivo 'context.xml' que se ubica en /WebContent/META-INF/

Editar esta configuración con su respectiva base de datos y puertos antes de editar el Controller y las consultas
