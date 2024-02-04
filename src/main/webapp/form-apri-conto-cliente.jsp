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
		
		<form action="/WebAppBancaMio/apri-conto-cliente" method="post">
		
			<p>Per favore, indicare i dati per il cliente di cui si vuole aprire il conto!!!.</p>
			
			<label for="iban">Iban</label>
			<br>
			<input type="text" id="iban" name="iban" minlength="32" maxlength="32" value="" pattern="^[A-Z]{2}[a-z]{2}[0-9]{28}$"  />
	
			<br>	
			
			<label for="valuta">Valuta</label><br>
			<input type="text" id="valuta" name="valuta" minlength="3" maxlength="3" value="" pattern="[A-Z]{3}"  />
	
			<br>

			<label for="codice-fiscale">Codice Fiscale</label><br>
			<input type="text" id="codice-fiscale" name="codice-fiscale" minlength="16" maxlength="16" value="" pattern="^[A-Z]{6}[0-9]{10}$"  />

			<br>
		
			<input type="submit" value="apri conto cliente">
			
		</form>
  
	</body>
</html>