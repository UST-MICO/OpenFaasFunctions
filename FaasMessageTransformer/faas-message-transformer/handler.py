import json
def handle(req):
    """handle a request to the function
    Args:
        req (str): request body
    """
    message = json.loads(req)
    if "extensions" not in message:
        message["extensions"] = []
    message["extensions"].append({"examplekey":"exampleValue"})
    return json.dumps([message])
