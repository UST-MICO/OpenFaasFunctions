"""
Element Remover
¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
This function can be used to filter out certain elements from an incoming message.
The elements, that shall be removed are referenced by the user in 'keys'.
"""

import json

# The keys of the elements, that shall be removed
keys = ['testKey1', 'testKey2']


def handle(req):
    """ Filters out all elements, that are referenced in 'keys'
    Args:
        req (str): request body
    """
    msg = json.loads(req)
    msg['data'] = {key: value for key, value in msg['data'].items()
               if key not in keys}
    return json.dumps(msg)

