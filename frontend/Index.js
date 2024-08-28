const express = require('express');
const mustacheExpress = require('mustache-express');
const app = express();
const path = require('path');
const axios = require('axios');
const ejs = require('ejs');

app.engine('html', mustacheExpress());
app.set('view engine', 'html');
app.set('views', __dirname + '/src/views');
app.set('view engine', 'ejs');

app.get('/', (req, res) => {
    res.render('PaginaInicial.html');
});

app.get('/upload', (req, res) => {
    res.render('Index.html');
});

app.get('/listarnotas', (req, res) => {
    axios.get('http://localhost:8080/notas').then((response) => {
        console.log(response.data);
        res.render('ListarNotas', {notas: response.data});
    })
})

app.get('/nota/:numero/:folha', (req, res) => {

    axios.get('http://localhost:8080/nota/' + req.params['numero'] + "/" + req.params['folha']).then((response) => {
        console.log(response.data);
        res.render('NotaFormatada', {nota: response.data});
    })
})



const PORT = 5000;
app.listen(PORT, function () {
    console.log("app rodando na porta " + PORT);
});