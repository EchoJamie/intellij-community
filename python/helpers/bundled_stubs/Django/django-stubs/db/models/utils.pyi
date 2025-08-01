from collections.abc import Iterable, Iterator, Mapping
from typing import Any, NamedTuple

from django.db.models.base import Model

def make_model_tuple(model: type[Model] | str | tuple[str, str]) -> tuple[str, str]: ...
def resolve_callables(mapping: Mapping[str, Any]) -> Iterator[tuple[str, Any]]: ...
def unpickle_named_row(names: Iterable[str], values: Iterable[Any]) -> NamedTuple: ...
def create_namedtuple_class(*names: str) -> type[NamedTuple]: ...

class AltersData:
    def __init_subclass__(cls, **kwargs: Any) -> None: ...
