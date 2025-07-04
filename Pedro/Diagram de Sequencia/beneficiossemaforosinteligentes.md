# Benefícios dos Semáforos Inteligentes no Controle Urbano de Tráfego

<br>

Para demonstrar os benefícios do uso de semáforos inteligentes, utilizei como base uma pesquisa de 2013 realizada pela Carnegie Mellon University.

<br>

### Sistema SURTRAC

<br>

O SURTRAC (Scalable Urban Traffic Control) é um sistema inteligente e descentralizado de controle de tráfego urbano baseado em agendas. Cada semáforo é controlado por um agente independente, instalado localmente, que toma decisões em tempo real com base nos dados dos sensores de veículos.

O sistema é dividido em quatro serviços principais:

* Comunicador: troca informações entre os serviços e entre cruzamentos;

* Detector: interpreta os dados dos sensores de veículos;

* Executor: controla os semáforos;

* Agendador: cria agendas para organizar o tempo verde dos sinais.

O SURTRAC é compatível com qualquer tipo de controlador ou sensor, pois usa mensagens padronizadas, o que facilita sua integração com diferentes hardwares e simuladores de tráfego.

<br>

### Projeto Piloto

<br>

Para demonstrar o desempenho do SURTRAC, um sistema piloto foi instalado em nove cruzamentos no bairro East Liberty, localizado em Pittsburgh, Pensilvânia. Conforme ilustrado na Figura 1, a distância entre esses cruzamentos varia entre 58 e 193 metros, com uma média de 116 metros, o que demanda uma coordenação bastante rigorosa entre eles.

<br>

Figura 1 - Mapa do local piloto com nove cruzamentos no bairro de East Liberty, em Pittsburgh, Pensilvânia.

![Figura 1](https://raw.githubusercontent.com/poo-ee-2025-1/g9/main/Pedro/Diagram%20de%20Sequencia/2a.png)


[1]

<br>

### Metodologia dos Testes

<br>

Para analisar o desempenho do sistema SURTRAC, foram realizados testes práticos em um trajeto composto pelas 12 rotas mais movimentadas do local piloto. Cada teste consistia em dirigir por todas essas rotas, apresentada na figura 2, que incluem os dois sentidos das avenidas Penn, Highland e Penn Circle, além de várias curvas no cruzamento da Penn com a Penn Circle e um trajeto partindo da Broad Avenue com conversão à esquerda.
Esses testes foram feitos em dois momentos distintos: antes, com os semáforos operando por planos fixos e atuados baseados no horário do dia; e depois, com os cruzamentos controlados pela lógica adaptativa do SURTRAC.
As viagens foram registradas com o aplicativo GPS Kit Pro (iPhone), que gerava o caminho completo percorrido, como ilustrado na Figura 3. Após isso, os dados foram tratados para extrair apenas os trechos relevantes e gerar indicadores de desempenho. 
Em cada cenário (antes e depois), foram feitas três rodadas de testes para quatro períodos diferentes do dia: manhã (8h-9h), meio-dia (12h-13h), tarde (16h-18h) e noite (18h-19h), totalizando 24 testes.

<br>

Figura 2 - Rotas de avaliação utilizadas no teste do sistema SURTRAC.

![Figura 2](https://raw.githubusercontent.com/poo-ee-2025-1/g9/main/Pedro/Diagram%20de%20Sequencia/2b.png)

[1]

<br>

Figura 3 - Exemplo de rastreamento por GPS de uma execução completa das rotas, com pontos A (início) e B (fim) do percurso.

![Figura 3](https://raw.githubusercontent.com/poo-ee-2025-1/g9/main/Pedro/Diagram%20de%20Sequencia/2c.png)

[1]

<br>

### Resultados

<br>

A Tabela 1 apresenta os resultados obtidos com a implantação do sistema adaptativo de controle de tráfego SURTRAC, em comparação ao sistema tradicional anterior. Os dados indicam melhorias significativas em todos os indicadores de desempenho e em diferentes horários do dia. Em termos de eficiência do tráfego, houve uma redução média de mais de 25% no tempo de viagem, aumento de 34% na velocidade média dos veículos, redução de mais de 31% no número de paradas e diminuição de mais de 40% no tempo de espera. Além disso, as emissões de poluentes incluindo CO₂, CO, NOx, hidrocarbonetos e compostos orgânicos voláteis foram reduzidas em 21%. Essas reduções nas emissões também são apresentadas de forma estimada na Tabela 2, que resume as economias projetadas com base nos dados de tráfego e consumo de combustível.
A maior melhoria foi observada durante o período do meio-dia, quando o sistema anterior apresentava mais dificuldades para lidar com o volume de tráfego. Nesse intervalo, 11 das 12 rotas avaliadas apresentaram ganhos em todas as métricas. Já nos períodos de pico da manhã, pico da tarde e noturno, 8 das 12 rotas mostraram melhorias. Durante o pico da manhã, três das quatro rotas que pioraram envolviam a região da Penn Circle, indicando possíveis falhas no antigo plano de temporização. No período de maior fluxo, o pico da tarde, o SURTRAC obteve melhorias em 9 das 12 rotas, com reduções no tempo de viagem de até 49%.
Esses resultados demonstram a eficácia do SURTRAC tanto na melhoria da mobilidade urbana quanto na redução dos impactos ambientais do tráfego.

<br>

Tabela 1 - Resultados do teste piloto

![tabela1](https://raw.githubusercontent.com/poo-ee-2025-1/g9/main/Pedro/Diagram%20de%20Sequencia/tabela.png)

[1]

<br>

Tabela 2 - Reduções estimadas de emissões

![tabela2](https://raw.githubusercontent.com/poo-ee-2025-1/g9/main/Pedro/Diagram%20de%20Sequencia/tabela2.png)

[1]

<br>

# Referências Bibliográficas

[1] Xie, X.; Smith, S.; Barlow, G. Schedule-driven intersection control. Carnegie Mellon University, 2012. Disponível em: https://www.ri.cmu.edu/pub_files/2013/1/13-0315.pdf
