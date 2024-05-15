package com.livefootballapp.api.actions.interfaces;

import com.livefootballapp.api.models.GameDto;

public interface GameObserver {
    void onGameFinished(GameDto gameDto);
}
