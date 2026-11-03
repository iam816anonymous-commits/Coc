# Research References

Research snapshot date: 2026-11-03. Recheck policies and Android behavior before release.

## Supercell — primary policy sources

- Terms of Service: https://supercell.com/en/terms-of-service/
  - Use limitations include restrictions on cheats, exploits, automation software, emulators, bots, hacks, mods, and unauthorized third-party software designed to modify or interfere with a game or game experience.
- Safe and Fair Play: https://supercell.com/en/safe-and-fair-play/
  - Describes unapproved third-party software that manipulates gameplay and lists bots/gameplay automation and mods/hacks.
- Clash of Clans Support, Don't Be a Cheat: https://sc-clashofclans.zendesk.com/hc/en-us/articles/54580505310491-Don-t-Be-a-Cheat
- Clash of Clans Fair Play policy update (2024-04-30): https://supercell.com/en/games/clashofclans/blog/misc/update-to-our-fair-play-policy-and-next-steps/

## Android — primary technical sources

- MediaProjection overview: https://developer.android.com/media/grow/media-projection
- WindowManager.LayoutParams: https://developer.android.com/reference/android/view/WindowManager.LayoutParams
- SYSTEM_ALERT_WINDOW permission: https://developer.android.com/reference/android/Manifest.permission#SYSTEM_ALERT_WINDOW
- Accessibility services: https://developer.android.com/guide/topics/ui/accessibility/service

## Research notes

Android platform capability does not grant permission under a game publisher's terms. In particular, Android's overlay permission is not evidence that Supercell permits an overlay during Clash of Clans gameplay.

The current research did not establish that a live strategy overlay is exempt from Supercell's third-party software restrictions. Treat it as blocked unless Supercell provides explicit written authorization.
