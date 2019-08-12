from element_remover.handler import *
from json import loads


class TestContentFilter:

    def test_handle(self):
        req = '{"testKey1": 1, "testKey2": 2, "testKey3": 3}'
        resp = handle(req)
        dict_resp = loads(resp)
        for k in keys:
            assert k not in dict_resp.keys()