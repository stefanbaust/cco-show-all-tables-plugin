# CCO compatibility in the customer portal

`cco-compatibility.json` declares supported CCO editions and feature packs for
new product releases. Keep it with the release source commit and review it
when the supported versions change. The release publisher sends this JSON
to the portal alongside the product version. Existing portal releases retain
their own metadata; a duplicate publish (HTTP 409) does not update them.

The initial declaration comes from v1.1.0:pom.xml cashDeskVersions declaration.
For this plugin, the JSON mirrors the explicit `cashDeskVersions` list in `pom.xml`; `n/a` is not a wildcard.

The portal currently records editions and feature packs, not individual patch
levels. Only declare a whole feature pack when that support can be stated;
do not infer support from a build dependency alone.

## Existing releases reviewed on 2026-09-14

- `1.0.0` (ab699a6e8139): ON_PREMISE FP21, CLOUD FP2502
- `v1.1.0` (6837edaed99b): ON_PREMISE FP21, CLOUD FP2502, CLOUD FP2503, CLOUD FP2601, CLOUD FP2602


Unknown or unlisted targets are not a claim of incompatibility. They need a
confirmed support declaration before being added to the portal filter.
