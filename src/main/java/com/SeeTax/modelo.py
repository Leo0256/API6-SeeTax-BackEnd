def previsao_tarifas(dados):
    from sklearn.linear_model import LinearRegression 
    regressor = LinearRegression()

    valores = []
    datas = []
    for a in dados:
        valores.append(a['valor_max'])
        datas.append(a['data'])

    regressor.fit(valores, datas)
    return regressor.predict(valores)
