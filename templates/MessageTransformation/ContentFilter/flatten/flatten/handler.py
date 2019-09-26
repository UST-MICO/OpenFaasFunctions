"""
Flatten
¯¯¯¯¯¯¯
This function can be used to flatten a message, whose elements are deeply nested in groups.
"""
import json
import collections


def flatten(d, parent_key='', sep='_'):
    """
    source: https://stackoverflow.com/a/6027615
    :param d: The dict, representing the json message
    :param parent_key: Auxiliary accumulator
    :param sep: A string that is used for separating the accumulated key
    :return: a flattened dict
    """
    items = []
    for k, v in d.items():
        new_key = parent_key + sep + k if parent_key else k
        if isinstance(v, collections.MutableMapping):
            items.extend(flatten(v, new_key, sep=sep).items())
        else:
            items.append((new_key, v))
    return dict(items)


def handle(req):
    """ Filters out all elements, that are referenced in 'keys'
    Args:
        req (str): request body
    """
    msg = json.loads(req)
    data = flatten(msg.get('data'))
    msg['data'] = data
    return json.dumps(msg)

