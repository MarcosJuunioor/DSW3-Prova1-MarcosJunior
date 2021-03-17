var serviceLocation = "ws://" + document.location.host + document.location.pathname + "tetris";
//Número de linhas e colunas do tabuleiro
var numColunas = 10, numLinhas = 20;
//Colunas do tabuleiro
var colunas = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];

var refreshIntervalId =null;

//Vai guardar as informações de cada jogador vindas do servidor
jogador = null;
oponente = null;

var flagInicio = true;

document.load = criarTabuleiro("tb1"), criarTabuleiro("tb2"), conectarAoServidorDoJogo();

//Função que cria tabuleiro dinamicamente
function criarTabuleiro(tab) {
    var tabuleiro = document.getElementById(tab);

    for (var i = 1; i <= numLinhas; i++) {

        var linha = document.createElement("tr");

        for (var j = 1; j <= numColunas; j++) {
            var celula = document.createElement("td");
            celula.setAttribute("id", tab + i + colunas[j - 1]);
            celula.setAttribute("class", "celulas");
            celula.setAttribute("style", "background:grey");
            linha.appendChild(celula);
        }
        tabuleiro.appendChild(linha);
    }

    document.onkeydown = function (e) {
        var keyCode = e.keyCode;
        
        if (keyCode === 39) {//se a seta da direita for clicada
            moverPecaAtualParaDireita();
        } else if (keyCode === 37) {//se a seta da esquerda for clicada
            moverPecaAtualParaEsquerda();
        } else if (keyCode === 38) {//se a seta para cima for clicada
            girarPecaAtualNoventaGrausParaDireita();
        }else if (keyCode === 40) {//se a seta para baixo for clicada
            moverPecaMaisRapido(); //Para mover mais rápido
        }
    };
    
    document.getElementById("reiniciar").addEventListener("click", function(e){
        location.reload();
        return false;
        
    });
    
    
}
/****           COMUNICAÇÃO COM O SERVIDOR          ****/

function conectarAoServidorDoJogo() {
    ws = new WebSocket(serviceLocation);

    ws.onmessage = lerDados;

    ws.onclose = function () {
        ws.close();
    };

    ws.onerror = () => {
        console.log("erro");
    };

}
//Esta função é executada, sempre que o servidor manda uma mensagem
function lerDados(evt) {
    var dados = JSON.parse(evt.data);

    if (dados.tipo === "OPEN") {
        if (jogador === null) {
            jogador = dados;
            document.getElementById("mensagem").innerHTML = "Aguardando o outro jogador entrar...";
        } else {
            oponente = dados;
            document.getElementById("mensagem").innerHTML = "Quando ambos os jogadores clicarem em iniciar, o jogo começa!";
            //Habilita o botão de iniciar
            document.getElementById("iniciar").removeAttribute("disabled");
        }
    } else if (dados.tipo === "MESSAGE") {

        //Sempre que o servidor enviar uma mensagem será pra atualizar os jogadores
        if (dados.nome === jogador.nome) {
            //jogador atualizado
            jogador = dados;
            //Atualização da posição da peça atual
            atualizarPecaAtual(jogador.tabuleiro.ID, jogador.tabuleiro.pecaAtual);
        } else if (dados.nome === oponente.nome) {
            if(flagInicio){
                document.getElementById("iniciar").disabled = true; 
                //Quando o jogo iniciar, limpa o campo de mensagem.
                document.getElementById("mensagem").innerHTML = "Divirta-se!";
            }
            //oponente atualizado
            oponente = dados;
            
            if(jogador.vencedor || oponente.vencedor){
                 ws.send("endgame");
            }
            
            //Atualização da posição da peça atual
            atualizarPecaAtual(oponente.tabuleiro.ID, oponente.tabuleiro.pecaAtual);

            if (typeof jogador.tabuleiro.proximaPeca !== "undefined") {
                document.getElementById("id-proxima-peca").innerHTML = jogador.tabuleiro.proximaPeca.tipoPeca;
            }
            /* Quando ambos os jogadores estão prontos, o jogo começa 
             e a peça atual irá se mover a cada 0,5s. */
            if (jogador.nome === "jogador1") {
                if (flagInicio) { // a flag é usada para que o intervalo de execução seja definido só uma vez.
                    refreshIntervalId = setInterval(movimentarPecasAtuaisParaBaixo, 200);
                    flagInicio = false;
                }
            }

        }




    } else if (dados.tipo === "ENDGAME") {
        
        if(dados.vencedor && dados.nome === jogador.nome){
            document.getElementById("mensagem").innerHTML = "Parabéns, você ganhou.";        
        }else{
            document.getElementById("mensagem").innerHTML = "Você perdeu.";        
        }
        document.getElementById("reiniciar").removeAttribute("disabled");
        clearInterval(refreshIntervalId);
        ws.close;
    }
}
;

//Envia ao servidor o nome do jogador que está pronto para iniciar o jogo
function comecarJogo() {
    document.getElementById("mensagem").innerHTML = "Aguardando o outro jogador iniciar.";        
    ws.send(jogador.nome);
}

//Fecha a sessão do jogador
document.getElementById("sair").addEventListener("click", function (e) {    
    ws.close();
});

/****           MOVIMENTAÇÕES DAS PEÇAS QUE ESTÃO CAINDO (PEÇAS ATUAIS) ****/

//Atualiza as celulas ocupadas pela peça de acordo com a tabela
function atualizarPecaAtual(tabela, pecaAtual) {
    if (typeof pecaAtual !== "undefined") {
        var cel1 = pecaAtual.celulas[0].linha + pecaAtual.celulas[0].coluna;
        var cel2 = pecaAtual.celulas[1].linha + pecaAtual.celulas[1].coluna;
        var cel3 = pecaAtual.celulas[2].linha + pecaAtual.celulas[2].coluna;
        var cel4 = pecaAtual.celulas[3].linha + pecaAtual.celulas[3].coluna;
        limparTabuleiro();
        document.getElementById(tabela + cel1).setAttribute("style", "background:" + pecaAtual.cor);
        document.getElementById(tabela + cel2).setAttribute("style", "background:" + pecaAtual.cor);
        document.getElementById(tabela + cel3).setAttribute("style", "background:" + pecaAtual.cor);
        document.getElementById(tabela + cel4).setAttribute("style", "background:" + pecaAtual.cor);
    }

}

//Limpa as células não ocupadas por células
function limparTabuleiro() {
    var tabuleiroJogador = jogador.tabuleiro;
    var celulasTabuleiroJogador = jogador.tabuleiro.celulas;
    var tabuleiroOponente = oponente.tabuleiro;
    var celulasTabuleiroOponente = oponente.tabuleiro.celulas;

    for (var i = 0; i < celulasTabuleiroJogador.length; i++) {
        if (!celulasTabuleiroJogador[i].ocupada && !celulaUsadaPelaPecaAtual(celulasTabuleiroJogador[i], tabuleiroJogador.pecaAtual)) {
            document.getElementById(tabuleiroJogador.ID + celulasTabuleiroJogador[i].linha + celulasTabuleiroJogador[i].coluna).setAttribute("style", "background:grey");
        }
        if (!celulasTabuleiroOponente[i].ocupada && !celulaUsadaPelaPecaAtual(celulasTabuleiroOponente[i], tabuleiroOponente.pecaAtual)) {
            document.getElementById(tabuleiroOponente.ID + celulasTabuleiroOponente[i].linha + celulasTabuleiroOponente[i].coluna).setAttribute("style", "background:grey");
        }
    }
}

//Verifica se a célula é igual a alguma das células usadas pela peça atual
function celulaUsadaPelaPecaAtual(celula, pecaAtual) {

    if (typeof pecaAtual !== "undefined") {
        if (celula.linha === pecaAtual.celulas[0].linha && celula.coluna === pecaAtual.celulas[0].coluna) {
            return true;
        } else if (celula.linha === pecaAtual.celulas[1].linha && celula.coluna === pecaAtual.celulas[1].coluna) {
            return true;
        } else if (celula.linha === pecaAtual.celulas[2].linha && celula.coluna === pecaAtual.celulas[2].coluna) {
            return true;
        } else if (celula.linha === pecaAtual.celulas[3].linha && celula.coluna === pecaAtual.celulas[3].coluna) {
            return true;
        }
    }
    return false;
}

//Função será chamada a cada 1s (será o movimento continúo da peca, até que ela pare)
function movimentarPecasAtuaisParaBaixo() {
    //requisita ao servidor a atualização das peças atuais
    ws.send("movimentarPecasParabaixo");
}

//A peça será movida uma casa para baixo quando o jogador clicar na seta para baixo
function moverPecaMaisRapido() {
    ws.send("moverPecaMaisRapido");
}

//A peça será girada noventa graus quando o jogador clicar na seta para cima
function girarPecaAtualNoventaGrausParaDireita() {
    //requisita ao servidor que a peça atual seja girada noventa graus para direita, quando o jogador clicar na seta para cima
    ws.send("girarPecaAtualNoventaGrausParaDireita");
}

//A peça será movida uma casa para direita, quando o jogador clicar na seta para direita
function moverPecaAtualParaDireita() {
    //requisita ao servidor a movimentação da peça atual do jogador para direita
    ws.send("moverPecaAtualParaDireita");
}

//A peça será movida uma casa para esquerda, quando o jogador clicar na seta para esquerda
function moverPecaAtualParaEsquerda() {
    //requisita ao servidor a movimentação da peça atual do jogador para esquerda
    ws.send("moverPecaAtualParaEsquerda");
}











