"""
Flatten
¯¯¯¯¯¯¯
This function can be used to flatten a message, whose elements are deeply nested in groups.
"""
from json import dumps, loads
import base64


def transform(txt):
    if len(txt) > 0:
        words = txt.split(' ')
        for i in range(0, len(words)):
            if i % 3 == 0:
                words[i] = 'MICO'
        return ' '.join(words)
    else:
        return ''


def handle(req):
    """ Filters out all elements, that are referenced in 'keys'
    Args:
        req (str): request body
    """
    msg = loads(req)
    old_body = base64.b64decode(msg['data']['base64body']).decode()
    new_body = base64.b64encode(transform(old_body).encode()).decode()

    msg['data']['base64body'] = new_body
    return dumps(msg)

