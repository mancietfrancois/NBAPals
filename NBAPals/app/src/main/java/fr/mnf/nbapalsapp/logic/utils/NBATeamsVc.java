package fr.mnf.nbapalsapp.logic.utils;

import fr.mnf.nbapals.nbamodel.utils.Teams;
import fr.mnf.nbapalsapp.R;

/**
 * Created by Francois on 31/10/2015.
 */
public enum NBATeamsVc {
    ATL(Teams.ATL, R.drawable.vc_atl),
    BKN(Teams.BKN, R.drawable.vc_bkn),
    BOS(Teams.BOS, R.drawable.vc_bos),
    CHA(Teams.CHA, R.drawable.vc_cha),
    CHI(Teams.CHI, R.drawable.vc_chi),
    CLE(Teams.CLE, R.drawable.vc_cle),
    DAL(Teams.DAL, R.drawable.vc_dal),
    DEN(Teams.DEN, R.drawable.vc_den),
    DET(Teams.DET, R.drawable.vc_det),
    GS(Teams.GS, R.drawable.vc_gs),
    HOU(Teams.HOU, R.drawable.vc_hou),
    IND(Teams.IND, R.drawable.vc_ind),
    LAC(Teams.LAC, R.drawable.vc_lac),
    LAL(Teams.LAL, R.drawable.vc_lal),
    MEM(Teams.MEM, R.drawable.vc_mem),
    MIA(Teams.MIA, R.drawable.vc_mia),
    MIL(Teams.MIL, R.drawable.vc_mil),
    MIN(Teams.MIN, R.drawable.vc_min),
    NO(Teams.NO, R.drawable.vc_no),
    NY(Teams.NY, R.drawable.vc_ny),
    OKC(Teams.OKC, R.drawable.vc_okc),
    ORL(Teams.ORL, R.drawable.vc_orl),
    PHI(Teams.PHI, R.drawable.vc_phi),
    PHX(Teams.PHX, R.drawable.vc_phx),
    POR(Teams.POR, R.drawable.vc_por),
    SA(Teams.SA, R.drawable.vc_sa),
    SAC(Teams.SAC, R.drawable.vc_sac),
    TOR(Teams.TOR, R.drawable.vc_tor),
    UTAH(Teams.UTAH, R.drawable.vc_utah),
    WSH(Teams.WSH, R.drawable.vc_wsh);

    private Teams mTeam;
    private int mDrawable;

    NBATeamsVc(Teams team, int drawable) {
        mTeam = team;
        mDrawable = drawable;
    }

    public static int findVCFromTeam(Teams team) {
        for (NBATeamsVc vcTeam : NBATeamsVc.values()) {
            if (vcTeam.mTeam == team) {
                return vcTeam.mDrawable;
            }
        }
        return -1;
    }

    public int getDrawable() {
        return mDrawable;
    }

}
