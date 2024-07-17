const express = require('express');
const mustacheExpress = require('mustache-express');
const app = express();
const path = require('path');
const axios = require('axios');

app.engine('html', mustacheExpress());
app.set('view engine', 'html');
app.set('views', __dirname + '/src/views');


app.get('/', (req, res) => {
    res.render('Index.html');
});

const PORT = 5000;
app.listen(PORT, function () {
    console.log("app rodando na porta " + PORT);
});