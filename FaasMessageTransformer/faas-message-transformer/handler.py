import json
def handle(req):
    """handle a request to the function
    Args:
        req (str): request body
    """
    message = json.loads(req)
    message["exampleKey"] = "exampleValue"
    return json.dumps([message])
