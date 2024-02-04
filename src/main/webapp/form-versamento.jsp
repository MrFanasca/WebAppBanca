<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

	<head>
		<title>Webapp Banca</title>
	</head>
	
	<body>
		 <center>
            <h1>Apri Conto Cliente</h1>
        </center>
        
        	<form action="/WebAppBancaMio/versamento" method="post">
        	
        		<p>Per favore, indicare i dati per inserire il versamento!!!.</p>
	
				<label for="iban">Iban</label>
				<br>
				<input type="text" id="iban" name="iban" minlength="32" maxlength="32" value="" pattern="^[A-Z]{2}[a-z]{2}[0-9]{28}$"  />
		
				<br>
				
				<label for="importo">Importo</label><br>
				<input type="text" id="importo" name="importo" min="0" value="" />
		
				<br>
				
				<input type="submit" value="aggiungi versamento">
			
			</form>
	</body>
</html>