from flatten.handler import *
from json import loads


class TestFlatten:

    def test_handle(self):
        req = '{\
            "specversion" : "0.2",\
            "type" : "io.github.ust.mico.result",\
            "source" : "/router",\
            "id" : "A234-1234-1234",\
            "time" : "2019-05-08T17:31:00Z",\
            "contenttype" : "application/json",\
            "data" : {\
                "key" : {"data": {"a": 1, "c": {"a": 2, "b": {"x": 5, "y" : 10}}, "d": [1, 2, 3]}}}\
            }'
        resp = loads(handle(req))

        assert resp['data'] == {'key_data_a': 1,
                                'key_data_c_a': 2,
                                'key_data_c_b_x': 5,
                                'key_data_c_b_y': 10,
                                'key_data_d': [1, 2, 3]}


