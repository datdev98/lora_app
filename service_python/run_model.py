import pickle
import sys

loaded_model = pickle.load(open('/home/datdev/Workspace/lora_app/service_python/model_logistic.sav', 'rb'))
def predict_value(X_value):
    y_value = loaded_model.predict(X_value)
    return int(y_value[0])

if __name__ == "__main__":
    if len(sys.argv) == 4:
        humidity = float(sys.argv[1])
        light = float(sys.argv[2])
        temperature = float(sys.argv[3])
        value = [[humidity, light, temperature]]
        a = predict_value(value)
        sys.exit(a)
    else: 
        sys.exit(-1)