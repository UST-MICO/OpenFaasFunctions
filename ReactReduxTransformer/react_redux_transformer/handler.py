"""
Flatten
¯¯¯¯¯¯¯
This function can be used to flatten a message, whose elements are deeply nested in groups.
"""
from json import dumps, loads
from urllib.parse import urlparse
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
    """ Replaces every third word in the article body with 'MICO' when posting a new article.
    Args:
        req (str): request body
    """
    msg = loads(req)

    path = urlparse(msg.get('adapterRequestUrl', '')).path
    method = msg.get('adapterRequestMethod', '')

    if (path.endswith('/articles') or path.endswith('/articles/')) and method == 'POST':
        old_body = base64.b64decode(msg['data']['base64body']['article']['body']).decode()
        new_body = base64.b64encode(transform(old_body).encode()).decode()
        msg['data']['base64body']['article']['body'] = new_body
    return dumps([msg])

