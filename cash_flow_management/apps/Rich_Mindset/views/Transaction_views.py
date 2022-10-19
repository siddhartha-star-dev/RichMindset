from drf_psq import PsqMixin, Rule
from PIL import Image
from rest_framework import status
from rest_framework.mixins import (
    CreateModelMixin,
    DestroyModelMixin,
    ListModelMixin,
    RetrieveModelMixin,
    UpdateModelMixin,
)
from rest_framework.parsers import MultiPartParser
from rest_framework.permissions import IsAuthenticated
from rest_framework.response import Response
from rest_framework.viewsets import GenericViewSet

from cash_flow_management.apps.Rich_Mindset.models.Transaction_model import (
    Transaction,
)
from cash_flow_management.apps.Rich_Mindset.serializers import (
    TransactionSerializer,
)
from cash_flow_management.receipt_ocr_main.main import predict


class TransactionViewSet(
    CreateModelMixin,
    RetrieveModelMixin,
    ListModelMixin,
    UpdateModelMixin,
    DestroyModelMixin,
    PsqMixin,
    GenericViewSet,
):
    http_method_names = ["patch", "post", "get", "delete"]
    pagination_class = None
    psq_rules = {
        "list": [Rule([IsAuthenticated], TransactionSerializer)],
        "retrieve": [Rule([IsAuthenticated], TransactionSerializer)],
        "create": [Rule([IsAuthenticated], TransactionSerializer)],
        "partial_update": [Rule([IsAuthenticated], TransactionSerializer)],
        "destroy": [Rule([IsAuthenticated], TransactionSerializer)],
    }

    def get_queryset(self):
        return Transaction.objects.all()

    def create(self, request, *args, **kwargs):
        serializer = self.get_serializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        object = self.perform_create(serializer)
        headers = self.get_success_headers(serializer.data)
        if object.receipt:
            image = Image.open(object.receipt)
            extracted_text = predict(image)
            return Response(
                {"id": object.id, "extracted_text": extracted_text},
                status=status.HTTP_201_CREATED,
            )

        return Response(
            serializer.data, status=status.HTTP_201_CREATED, headers=headers
        )

    def perform_create(self, serializer):
        return serializer.save(user=self.request.user)

    def perform_update(self, serializer):
        serializer.save(user=self.request.user)
