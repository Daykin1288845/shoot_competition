package com.shootCompetition.common.global;

import com.shootCompetition.domain.vo.ShootRankSummaryVO;

import java.util.ArrayList;
import java.util.List;

public class GlobalVariables {
    public static int currentCompetitionId;
    public static ShootRankSummaryVO SRS = new ShootRankSummaryVO();
    static {
        SRS.setList(new ArrayList<>());
    }


}
