import os
import warnings

import numpy as np

from .src.engine import DefaultEngine
from .src.model import DefaultModel

warnings.filterwarnings("ignore")

dir = os.path.dirname(os.path.abspath(__file__))
detector_cfg = os.path.join(dir, "configs/craft_config.yaml")
detector_model = os.path.join(dir, "src/model/craft_mlt_25k.pth")
recognizer_cfg = os.path.join(dir, "configs/star_config.yaml")
recognizer_model = os.path.join(
    dir,
    "src/model/TPS-ResNet-BiLSTM-Attn-case-sensitive.pth",
)

model = DefaultModel(
    detector_cfg, detector_model, recognizer_cfg, recognizer_model
)
engine = DefaultEngine(model)


def predict(image):
    image = np.array(image)

    engine.predict(image)
    return engine.result
