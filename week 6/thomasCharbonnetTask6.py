
import flask as fs
import requests, json
from flask import request, jsonify



def n_num_getter(scores, n):
    return list(scores)[n]

app = fs.Flask(__name__)
app.config["DEBUG"] = True

### I 
url = "https://4v9r83qfo4.execute-api.eu-central-1.amazonaws.com/dev"
### I creat a "Get" request in order to capture the data of the target
response = requests.get(url)
### I transform the data received to a json data
myData = json.loads(response.text);



### I generate a get method in order to print data on my web page
@app.route('/', methods=['GET'])
def all_data():
    return jsonify(myData)### i print all my json structure on the web page



### i generate a new web page with the name of the course
@app.route('/courseName', methods=['GET'])
def courseName_data():
    ### i only print myData["courseName"] on the page localhost:5000/courseName
    return jsonify(myData["courseName"])



@app.route('/newJson', methods=['POST'])
def add_score():

    info = {'devise' : request.json["hello world"]}### i create a new json element

    myData.append(info)### i add this element on my json file

    return jsonify(myData)### i print my new json element 



### I generate a new request in order to delete data
@app.route('/scores/<int:n>', methods=['DELETE'])

def score_delete(n):
    index = n_num_getter(data["scores"], n)### I capture the index of the target
    
    if (index != "Not so much scores yet!"):### I check if the index exist on my liste
        
        to_return = {index: (data["scores"])[index]}
        (myData["scores"]).pop(index)### I delete the target of the list
        return jsonify(to_return)### I print the new list without the target
    
    else:
        
        return ("Invalid Index")### I print a error 




app.run()
