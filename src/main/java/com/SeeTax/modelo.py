def previsao_tarifas(str_json):
    regressor = LinearRegression()

    # Define o json array
    json_data = json.loads(str_json.replace("\'", "\""))

    # Organiza os dados em 2 arrays
    valores = []
    datas = []
    for a in json_data:
        valores.append(a['valor_max'])

        d = format_date(a['data'])
        datas.append(time.mktime(d.timetuple()))

    # Eixo X e Y do modelo
    X = np.array(datas).reshape(-1, 1)
    Y = np.array(valores).reshape(-1, 1)

    # Treina a previsão
    regressor.fit(X, Y)

    # Obtêm o próximo mês para a previsão
    last_date = format_date(json_data[-1]['data'])
    next_month = last_date + timedelta(mdays[last_date.month])
    predict_next = time.mktime(next_month.timetuple())

    # Faz a previsão
    predict = regressor.predict(np.array([[predict_next]]))

    # Retorna a previsão
    print({
        "data": next_month.date().strftime('%b/%y'),
        "valor_max": predict[0][0]
    })

# Formata a string para date
def format_date(date_str):
    return datetime.strptime(date_str, '%b/%y')

import sys
import json
from datetime import datetime
from datetime import  date, timedelta
from calendar import mdays
import time
import locale
import numpy as np
from sklearn.linear_model import LinearRegression

locale.setlocale(locale.LC_TIME, "pt_BR")

previsao_tarifas(sys.argv[1])