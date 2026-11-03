# Supercell Policy Research

**Research date:** 2026-11-03  
**Status:** Product risk identified; live in-game overlay is blocked pending explicit written authorization.

## Primary sources reviewed

1. Supercell Terms of Service: https://supercell.com/en/terms-of-service/
2. Supercell Safe and Fair Play: https://supercell.com/en/safe-and-fair-play/
3. Clash of Clans Support — Don't Be a Cheat: https://sc-clashofclans.zendesk.com/hc/en-us/articles/54580505310491-Don-t-Be-a-Cheat
4. Clash of Clans Fair Play policy update: https://supercell.com/en/games/clashofclans/blog/misc/update-to-our-fair-play-policy-and-next-steps/

## Findings

The Terms of Service Use Limitations prohibit cheats, exploits, automation software, emulators, bots, hacks, mods, and unauthorized third-party software designed to modify or interfere with a Supercell game or game experience. They also prohibit unauthorized modification of game files and attempts to derive underlying game source code through unauthorized methods.

The Safe and Fair Play policy describes third-party software as unapproved apps that manipulate gameplay to provide unfair advantages. It lists hacks/mods, bots/gameplay automation, and other programs intended to modify gameplay or provide unearned progress. The policy warns of permanent bans for offending accounts.

The Clash of Clans support article reiterates that bots and mods violate the Terms and can lead to account penalties. Supercell's 2024 policy update says it permanently bans accounts identified as bots and players using third-party software.

## Consequence for this project

The user's preferred product is a floating Android overlay that analyzes a live enemy base and recommends troop placement coordinates, composition, timing, and spell placement while the game is running.

Even if the user makes every tap manually and the app never injects input, a live overlay that gives tactical guidance during active gameplay may be treated as unauthorized third-party software or an unfair gameplay advantage. The public policy language does not provide a clear exemption for manual-only overlays. We must not represent it as safe or permitted.

Using a new/secondary account does not change the policy and does not eliminate ban risk.

## Product boundary

### Blocked unless Supercell gives explicit written authorization

- Floating overlay displayed over the live Clash of Clans client.
- Live capture/analysis of active game screens for in-match recommendations.
- Automated troop placement, taps, swipes, or other gameplay control.
- Accessibility-service interaction with the game.
- Game-client modification, memory reading, packet inspection, private API use, or reverse engineering.
- Anti-cheat detection avoidance or concealment.

### Research path that can proceed independently

- A standalone planner that accepts screenshots explicitly imported by the user.
- Offline image annotation and base-layout analysis outside the live game session.
- Generic strategy education and attack-planning simulations.
- User-entered Town Hall, troop levels, army capacity, spells, heroes, and equipment.
- Evaluation against synthetic or user-provided static layouts.

This alternative is not a guarantee of Supercell approval. Before public distribution or use in conjunction with Clash of Clans, seek written clarification from Supercell.

## Open policy question

Ask Supercell in writing whether a standalone screenshot-import planner, with no live overlay, no automation, no game-client access, and no game data extraction, is acceptable. Separately request explicit authorization before considering any in-game overlay.

## Decision

**Do not implement the live overlay at this stage.** Continue with a standalone screenshot planner and preserve a policy gate in the roadmap.
