from flask import Flask, request, jsonify
import langchainMod
import agingImg

app = Flask(__name__)

@app.route('/')
def index():
    print("index Start")
    return "hi, Flask"

def on_json_loading_failed_return_dict(e):
    return {}

# 실종자 데이터가 등록될 때마다 호출하여 실종 정보 데이터를 랭체인을 통해 축적하기 위한 함수
@app.route('/predict/insertInfo', methods=['POST'])
def insertInfo():

    request.on_json_loading_failed = on_json_loading_failed_return_dict
    print("langchainPost Start")

    data = request.get_json()
    info = data["info"]
    print(info)
    # 랭체인을 통한 데이터 임베딩 및 저장 함수 호출
    result = langchainMod.insertMissingData(info)

    return jsonify({"result": result})  # 받아온 데이터를 다시 전송

# 축적된 실종자 데이터 기반 유사도 분석 수행 및 결과 반환을 위한 함수
@app.route('/predict/queryResult', methods=['POST'])
def queryResult():

    request.on_json_loading_failed = on_json_loading_failed_return_dict
    langchainMod.insertFirst()
    print("queryResult Start")

    result = request.get_json()
    query = result["query"]
    print(query)

    # 랭체인을 통한 질의 전달 및 응답 반환 함수 호출
    result = langchainMod.queryResult(query)
    return jsonify({"result": result})  # 받아온 데이터를 다시 전송


# 축적된 실종자 데이터 기반 유사도 분석 수행 및 결과 반환을 위한 함수
@app.route('/predict/queryResultByRetrive', methods=['POST'])
def queryResultByRetrive():

    request.on_json_loading_failed = on_json_loading_failed_return_dict
    langchainMod.insertFirst()
    print("queryResult Start")

    result = request.get_json()
    query = result["query"]
    print(query)

    # 랭체인을 통한 질의 전달 및 응답 반환 함수 호출
    result = langchainMod.queryResultByRetrive(query)
    return jsonify({"result": result})  # 받아온 데이터를 다시 전송

@app.route('/predict/first', methods=['POST'])
def insertMissingData():

    request.on_json_loading_failed = on_json_loading_failed_return_dict
    print("insertFirst Start")

    result = langchainMod.insertMissingData()

    return jsonify({"result": result})  # 받아온 데이터를 다시 전송

@app.route('/predict/Aging', methods=['POST'])
def aging():
    request.on_json_loading_failed = on_json_loading_failed_return_dict
    print("queryResult Start")

    result = request.get_json()
    print("result")

    result = agingImg.create(result['image'], result['target_age'])

    return jsonify({"resultURL": result})  # 받아온 데이터를 다시 전송

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True) #모든 IP 접속 허용
