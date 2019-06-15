<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Gerencia Pais</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body>
    <div class="container">
        <div class="jumbotron">
            <h1>Gerenciamento de País</h1>
            <p>Essa página é responsável por fazer o geranciamento de paises. </p>
        </div>
        <div class="row">
            <div class="col">
                <form action="/pais/alterar" method="post">
                    <div class="form-group">
                        <label for="nome">Nome:</label>
                        <input value="${(paisAtual.nome)!}" name="nome" type="text" class="form-control" id="nome">
                    </div>
                    <div class="form-group">
                        <label for="sigla">Sigla:</label>
                        <input value="${(paisAtual.sigla)!}"  name="sigla" type="text" class="form-control" id="sigla">
                    </div>
                    <div class="form-group">
                        <label for="codigo">Código Telefone:</label>
                        <input value="${(paisAtual.codigoTelefone)!}"  name="codigoTelefone" type="number" class="form-control" id="codigo">
                    </div>

                    <input type="hidden" name="id" value="${(paisAtual.id)!}"></input>

                    <button type="submit" class="btn btn-warning">Alterar</button>
                </form>

            </div>
        </div>
        <div class="row">
            <div class="col">
                <table class="table table-striped table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th>Nome</th>
                            <th>Sigla</th>
                            <th>Código Telefone</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list paises as pais>
                            <tr>
                                <td>${pais.nome}</td>
                                <td>${pais.sigla}</td>
                                <td>${pais.codigoTelefone}</td>
                                <td>
                                    <a href="/pais/prepara-alterar?id=${pais.id}">Alterar</a>
                                    <a href="/pais/excluir?id=${pais.id}">Excluir</a>
                                </td>
                            </tr>        
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>

</html>