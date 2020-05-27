package DomainLayer.LeagueSeasonsManagment;

import DomainLayer.Games.Game;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * interface for game policies
 */
public interface IGameInlayPolicy {
    HashMap<Integer, ArrayList<Game>> gameInlayPolicyAlgoImplementation();

    String getName();
}
