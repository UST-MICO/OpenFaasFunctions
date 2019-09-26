from react_redux_transformer.handler import *
import base64
from pathlib import Path
from json import loads


class TestReactReduxTransformer:

    @staticmethod
    def decode_base64body(s):
        return base64.b64decode(loads(s)['data']['base64body']['article']['body']).decode()

    def test_handle_empty_base64body(self):
        with open(Path('tests')/'resources'/'test_message_0.json', 'r') as f:
            request = f.read()
        response = handle(request)
        result_body = self.decode_base64body(response)
        assert result_body == ''

    def test_handle_body_empty_body(self):
        with open(Path('tests') / 'resources' / 'test_message_1.json', 'r') as f:
            request = f.read()
        response = handle(request)
        result_body = self.decode_base64body(response)
        assert result_body == 'MICO'

    def test_handle_body_long_body(self):
        with open(Path('tests') / 'resources' / 'test_message_2.json', 'r') as f:
            request = f.read()
        response = handle(request)
        result_body = self.decode_base64body(response)
        words = result_body.split(' ')
        for i in range(len(words)):
            word = words[i]
            if i % 3 == 0:
                assert word == 'MICO'
            else:
                assert word != 'MICO'

    def test_handle_body_wrong_url_path(self):
        with open(Path('tests') / 'resources' / 'test_message_3.json', 'r') as f:
            request = f.read()
        response = handle(request)
        result_body = self.decode_base64body(response)
        words = result_body.split(' ')
        assert 'MICO' not in words
