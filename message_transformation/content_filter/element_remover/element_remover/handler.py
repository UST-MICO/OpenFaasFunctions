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
    msg_in = json.loads(req)
    msg_out = {key: value for key, value in msg_in.items()
               if key not in keys}
    return json.dumps(msg_out)

